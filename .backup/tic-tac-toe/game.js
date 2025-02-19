document.addEventListener('DOMContentLoaded', () => {
    const cells = document.querySelectorAll('.cell');
    let board = [[' ', ' ', ' '], [' ', ' ', ' '], [' ', ' ', ' ']];
    let currentPlayer = 'X';

    cells.forEach(cell => {
        cell.addEventListener('click', () => {
            const index = cell.getAttribute('data-cell-index');
            const row = Math.floor(index / 3);
            const col = index % 3;

            if (board[row][col] === ' ') {
                board[row][col] = currentPlayer;
                cell.textContent = currentPlayer;
                cell.style.pointerEvents = 'none';

                // Send move to backend
                fetch('/move', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ row, col }),
                })
                .then(response => response.json())
                .then(data => {
                    if (data.winner) {
                        alert(`Player ${data.winner} wins!`);
                    } else if (data.draw) {
                        alert('It\'s a draw!');
                    } else {
                        currentPlayer = data.currentPlayer;
                    }
                });
            }
        });
    });
});