// Mojo doesn't support dictionary literals or expressions at the file scope level yet.
// Here's how we can structure the configurations in Mojo:

struct Configuration:
    var type: String
    var name: String
    var request: String
    var mainClass: String
    var projectName: String
    var console: String

struct LaunchConfig:
    var version: String
    var configurations: List[Configuration]

fn main():
    let launch_config = LaunchConfig(
        version="0.2.0",
        configurations=[
            Configuration(
                type="java",
                name="Debug Tic Tac Toe",
                request="launch",
                mainClass="com.example.tictactoe.TicTacToe",
                projectName="tic-tac-toe",
                console="integratedTerminal"
            ),
            Configuration(
                type="chrome",
                name="Launch Browser",
                request="launch",
                mainClass="",
                projectName="",
                console=""
            )
        ]
    )

    // Here you would typically use the `launch_config` to set up your debugging environment.
    // However, since Mojo doesn't support this directly, you might need to use a different approach or tool for debugging.

    // For example, you could print out the configuration details:
    print("Version:", launch_config.version)
    for config in launch_config.configurations:
        print("Type:", config.type)
        print("Name:", config.name)
        print("Request:", config.request)
        print("Main Class:", config.mainClass)
        print("Project Name:", config.projectName)
        print("Console:", config.console)
        print("---")
