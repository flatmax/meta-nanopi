DESCRIPTION = "Minimalistic bootloader for NanoPi"
HOMEPAGE = "https://github.com/ARMWorks/NanoPi-nanoboot"
SECTION = "bootloaders"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a23a74b3f4caf9616230789d94217acb"

SRCREV = "24adb19902ad48834800eb4e6fd6b87bd2faed85"
PV = "v0.0+git${SRCPV}"

SRC_URI = "git://github.com/ARMWorks/NanoPi-nanoboot.git;protocol=https;branch=master;rev=24adb19902ad48834800eb4e6fd6b87bd2faed85 \
           file://nanoboot.txt"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy


EXTRA_OEMAKE = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" V=1'
FILES_${PN} = "/boot ${sysconfdir}"

do_configure () {
}

do_compile () {
	oe_runmake
}

do_install () {
        install -d ${D}/boot
        install ${S}/build/nanoboot.bin ${D}/boot/
        install ${WORKDIR}/nanoboot.txt ${D}/boot/
}

do_deploy () {
        install -d ${DEPLOYDIR}
        install ${S}/build/nanoboot.bin ${DEPLOYDIR}/
        install ${WORKDIR}/nanoboot.txt ${DEPLOYDIR}/
}

addtask deploy before do_build after do_compile
