import shutil
import datetime


def create_zip(destination: str):
    shutil.make_archive(destination + "/mcdisc-pack-{}".format(datetime.datetime.now().date()), "zip", destination)
