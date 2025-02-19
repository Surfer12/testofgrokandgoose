# Project Creation Rules

## Rule 1: Language and Interoperability

- **Always use Mojo and never Python**: Use Mojo as the primary language for project development. Utilize the interoperability of Python, Mojo, and Java where needed. **Python can be used for scripting or data analysis, while Java SDKs are used for specific integrations.**

## Rule 2: Java SDKs

- **Always use Java SDKs from LLM Providers**: Ensure that any Java SDKs used in the project are sourced from LLM Providers. **Ensure compatibility with Mojo, and document any known issues or workarounds.**

## Rule 3: Project Management

- **Always use magic from modular as a Python project manager**: Use magic from modular to manage the project structure and dependencies. **This tool was chosen for its seamless integration with Mojo and its ability to handle complex project structures.**

  - **Initialize the Project**: Use `magic init` to set up the project structure.
  - **Add Dependencies**: Use `magic add` to add necessary dependencies.
  - **Install Dependencies**: Use `magic install` to install all dependencies.
  - **Update Dependencies**: Use `magic update` to update dependencies as recorded in the local lock file.
  - **Upgrade Dependencies**: Use `magic upgrade` to update packages to the latest possible version, disregarding the manifest version constraints.
  - **Build the Project**: Use `magic build` to compile or build the project.
  - **Create a Shell Environment**: Use `magic shell` to create a shell environment with all the necessary dependencies and configurations for your project.

## Rule 4: Directive Continuation

- **Always continue with the directive unless canceled by the user**: Follow the outlined process unless explicitly canceled by the user. **To cancel, use the command `cancel directive`.**

## Rule 5: Personalization

- **Always call me Ryan, that's me, a unique human!**: Address me as Ryan in all communications and documentation. 

## Rule 6: Debugging

- **Always use launch.json for debugging**: Configure the `launch.json` file to set up debugging for Java and browser launching.

## Rule 7: Documentation

- **Always document known issues and workarounds**: Document any known issues or workarounds related to Java SDKs, Mojo, or project management tools.

## Rule 8: Max Package Structure

- **Max Package**: The `max` package contains the following modules for project builds:

  - **max-core**: Core utilities for Mojo projects.
  - **mblack**: Mojo's black box for interoperability with Python and Java.
    - **python**: Python runtime environment.
    - **click**: Command-line interface utilities.
    - **mypy_extensions**: Extensions for mypy type checking.
    - **packaging**: Utilities for packaging Python projects.
    - **pathspec**: Path specification utilities.
    - **platformdirs**: Platform-specific directories.
    - **typing_extensions**: Additional typing constructs.
  - **max-python**: Python-specific utilities for Mojo projects.
    - **numpy**: Numerical computing library.
    - **sentencepiece**: Tokenization library.
    - **tqdm**: Progress bar library.
  - **mojo-jupyter**: Jupyter integration for Mojo.
    - **jupyter_client**: Jupyter client for kernel communication.
    - **tornado**: Web server for Jupyter notebooks. 