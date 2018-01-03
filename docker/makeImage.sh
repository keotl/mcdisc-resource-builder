docker image rm keotl/mcdisc-resource-builder --force
docker image build . -t mcdisc-resource-builder
docker tag mcdisc-resource-builder keotl/mcdisc-resource-builder
docker push keotl/mcdisc-resource-builder
