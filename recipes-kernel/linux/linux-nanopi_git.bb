require recipes-kernel/linux/linux.inc
DESCRIPTION = "Linux kernel for FriendlyARM NanoPi"
KERNEL_IMAGETYPE = "zImage"

COMPATIBLE_MACHINE = "(nanopi)"

PV = "4.1.2"
PR = "r0"
SRCREV_pn-${PN} = "dae0b8afa68b66e36e171ad209a667851a2de4fe"

SRC_URI += "git://github.com/ARMWorks/NanoPi-Linux-4.1.y.git;protocol=https;branch=master \
            file://0003-fix-gcc6.patch \
            file://defconfig"

S = "${WORKDIR}/git"
