import mcdiscbuilder
import argparse

TEST_URL = "https://raw.githubusercontent.com/keotl/mcdisc/master/sample-disc-config.json"
TARGET_DIRECTORY = "."

parser = argparse.ArgumentParser(description="mcdisc resource pack builder")
parser.add_argument(help="Disc list location. Can be an URL, or a file path.", dest="disc_list")
args = parser.parse_args()

mcdiscbuilder.build_resource_pack(args.disc_list, TARGET_DIRECTORY)

