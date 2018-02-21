Note
====
As of February 2018, YouTube's download API seems to have changed, rendering this builder useless. A new version is on the way.

mcdisc-resource-builder
======================

This is a simple automated resource pack builder for the Minecraft Discs mod. See [mcdisc](https://github.com/KEOTL/mcdisc).
As-is, the software works, but could be much improved. This is a "proof-of-concept" type of software.

I do not own ffmpeg or 7zip, which this software uses and includes for convenience.

## Setup
1. Download the builder, either by cloning the `release` directory of this repository, or by downloading a zipped version from [here](https://s3.us-east-2.amazonaws.com/mcdisc/mcdisc-builder-1.1.1b.zip).
2. Edit the run script corresponding to your operating system to build your own disc list.
    - For Windows, edit the `run.bat` file, for Mac OS X, the `run.mac.sh`, and for Linux, edit the `run.sh` file.
    - Locate the line which starts with `java` and replace the `--disc-list` parameter value for your own. The value can either be a file path, or an http URL.
3. Run the edited run script.
4. Copy the created mcdisc-resource-pack.zip into your minecraft resource packs folder.
5. Enable the resource pack from the in-game menu.

### Notes
- On Windows, the builder sometimes seems to 'freeze' while downloading, and no longer seems to make progress. Press 'Enter' inside the command prompt window to 'wake' the program. This should not corrupt your resource pack.
- Download errors are normal and often happen while downloading multiple videos at once. The builder automatically retries the download.
- Customize the run script further by edition the `--pool-size` and `--max-retry` arguments.
