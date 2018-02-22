import mcdiscbuilder

TEST_URL = "https://raw.githubusercontent.com/keotl/mcdisc/master/sample-disc-config.json"
TARGET_DIRECTORY = "/tmp/testmcdisc"

mcdiscbuilder.build_resource_pack(TEST_URL, TARGET_DIRECTORY)

