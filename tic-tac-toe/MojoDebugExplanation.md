# VS Code Debug Configuration: .json vs .mojo

## Overview

Visual Studio Code (VS Code) traditionally uses a file named `launch.json` to configure debugging settings. This JSON format provides a widely adopted, standardized way for various extensions and tools to define how your application should be launched and debugged.

## Why Both .json and .mojo?

- **launch.json (Traditional Format):**
  - This is the conventional configuration file for VS Code debug settings.
  - It is used by many extensions to set up debugging parameters in a standardized JSON format.

- **launch.mojo (Mojo-Specific Requirement):**
  - When debugging Mojo applications, the environment requires a file with a `.mojo` extension.
  - This extension signals the Mojo debugger to parse the configuration based on Mojo-specific rules and behaviors rather than using the standard JSON parser.

## Summary

Having both files ensures that:
- The standard `launch.json` format is available for general VS Code debugging setups.
- The specialized `.mojo` file is used when working specifically with Mojo, ensuring correct handling of project configurations.

In our project, renaming or duplicating the configuration to a `.mojo` file allows developers to seamlessly debug Mojo applications while still benefiting from the traditional VS Code configuration mechanisms in other contexts.
