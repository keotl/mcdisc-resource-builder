mcdisc-resource-builder
======================

This is a simple automated resource pack builder for the Minecraft Discs mod. See [mcdisc](https://github.com/KEOTL/mcdisc).

## Download the builder executable
Windows: [https://ci.appveyor.com/project/keotl/mcdisc-resource-builder/build/artifacts](https://ci.appveyor.com/project/keotl/mcdisc-resource-builder/build/artifacts) (Click of mcdisc-resource-builder.zip)

MacOS: (Coming soon)

Linux: (Coming soon)

## Setup
1. Download the builder for your operating system. (See above)
2. Double click on the executable corresponding to your operating system. (Confirmed working on Windows 10, macOS High Sierra, Ubuntu 17.10). If you run into issues, try running from source. (See below.)
3. Enter your disc list location in the text box. Can be either a URL, or a local file. Note : While building, the window will freeze. This is normal, and will unfreeze when the pack has been created.
4. Copy the created mcdisc-resource-pack.zip into your minecraft resource packs folder.
5. Enable the resource pack from the in-game menu.

### Notes
- The builder can be run without a GUI. see the `run_commandline` script for an example.

## Running from source
1. Clone this repository. Make sure python3, pip3 and ffmpeg are installed on your system.
2. Install the dependencies. (A virtual environment is recommended, but not mandatory.)
```bash
pip3 install -r requirements.txt
```
3. Run the builder.
```bash
python3 main.py
```
Or without a gui. 
```bash
python3 main.py <disc_list>
```

4. (Optional) Build a single executable file using PyInstaller.
```bash
pyinstaller -F main.py
```
