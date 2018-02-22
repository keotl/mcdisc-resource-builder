import mcdiscbuilder
import argparse

TEST_URL = "https://raw.githubusercontent.com/keotl/mcdisc/master/sample-disc-config.json"
TEMPORARY_DIRECTORY = "build"
ZIP_DESTINATION_DIRECTORY = "."

parser = argparse.ArgumentParser(description="mcdisc resource pack builder")
parser.add_argument(help="Disc list location. Can be an URL, or a file path.", dest="disc_list")
parser.add_argument("--destination", dest="destination",
                    help="zip file destination. Defaults to the current directory.", default=ZIP_DESTINATION_DIRECTORY)
parser.add_argument("--temporary-dir", dest="temporary_dir",
                    help="Temporary directory to build the pack. Defaults to 'build' in the current directory",
                    default=TEMPORARY_DIRECTORY)
args = parser.parse_args()

mcdiscbuilder.build_resource_pack(args.disc_list, args.temporary_dir, args.destination)
