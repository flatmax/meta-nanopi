require recipes-kernel/linux/linux.inc
DESCRIPTION = "Linux kernel for FriendlyARM NanoPi"
KERNEL_IMAGETYPE = "zImage"

COMPATIBLE_MACHINE = "(nanopi)"

PV = "4.1.2"
PR = "r0"
SRCREV_pn-${PN} = "c9efdea291a03fb607688459a4d3f4cd2a98fb9d"

SRC_URI += "git://github.com/friendlyarm/linux-4.x.y.git;protocol=https;branch=nanopi-v4.1.y \
            file://dhd-gcc49-nodebug.patch \
            file://dhd-fix-nodebug.patch \
            file://defconfig"

S = "${WORKDIR}/git"
