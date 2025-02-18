export fn getLaunchConfig() -> Map<String, dynamic> {
  return {
    "version": "0.2.0",
    "configurations": [
      {
        "type": "java",
        "name": "Debug Tic Tac Toe",
        "request": "launch",
        "mainClass": "com.example.tictactoe.TicTacToe",
        "projectName": "tic-tac-toe",
        "console": "integratedTerminal"
      },
      {
        "type": "chrome",
        "request": "launch",
        "name": "Launch Browser",
        "url": "http://localhost:8080",
        "webRoot": "${workspaceFolder}"
      }
    ]
  };
}
