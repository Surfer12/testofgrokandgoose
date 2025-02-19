from collections.dict import Dict
from collections.list import List
from collections.string import String


struct LaunchConfig:
    var config: Dict[String, String]
    var configurations: List[Dict[String, String]]

    fn __init__(inout self):
        self.config = Dict[String, String]()
        self.configurations = List[Dict[String, String]]()
        # Java debug configuration
        var java_config = Dict[String, String]()
        java_config["type"] = "java"
        java_config["name"] = "Debug Tic Tac Toe"
        java_config["request"] = "launch"
        java_config["mainClass"] = "com.example.tictactoe.TicTacToe"
        java_config["projectName"] = "tic-tac-toe"
        java_config["console"] = "integratedTerminal"
        self.configurations.append(java_config)

        # Browser debug configuration
        var browser_config = Dict[String, String]()
        browser_config["type"] = "chrome"
        browser_config["request"] = "launch"
        browser_config["name"] = "Launch Browser"
        browser_config["url"] = "http://localhost:8080"
        browser_config["webRoot"] = "${workspaceFolder}"
        self.configurations.append(browser_config)

        # Mojo debug configuration
        var mojo_config = Dict[String, String]()
        mojo_config["type"] = "mojo"
        mojo_config["name"] = "Debug Mojo Code"
        mojo_config["request"] = "launch"
        mojo_config["program"] = "${workspaceFolder}/main.mojo"
        mojo_config["console"] = "integratedTerminal"
        self.configurations.append(mojo_config)

        self.config["version"] = "0.2.0"
        # Convert configurations to JSON-like string
        var config_str = "["
        for i in range(len(self.configurations)):
            if i > 0:
                config_str += ","
            config_str += "{"
            var dict_items = self.configurations[i].items()
            for j in range(len(dict_items)):
                if j > 0:
                    config_str += ","
                config_str += '"' + dict_items[j].key() + '":"' + dict_items[j].value() + '"'
            config_str += "}"
        config_str += "]"
        self.config["configurations"] = config_str

    fn __call__(self) -> Dict[String, String]:
        return self.config

    fn __copyinit__(inout self, existing: Self):
        self.config = existing.config
        self.configurations = existing.configurations