require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "FriendlyArm's Nanopi-M4 Kernel"

DEPENDS += "openssl-native lzop-native lz4-native"
HOST_EXTRACFLAGS += "-I${STAGING_INCDIR_NATIVE}"

SRC_URI = " \
	git://github.com/friendlyarm/kernel-rockchip.git;branch=nanopi4-linux-v4.4.y;protocol=https; \
	file://nanopim4_working_defconfig;subdir=git/arch/arm64/configs \
	file://0001-slow-baud-rate.patch \
	file://0004-bootargs-rootfs.patch \
"

S = "${WORKDIR}/git"

SRCREV = "fc81430e07193304d7b281b90f977a7b0db5fee4"
PV = "4.4.167"

FILES_${KERNEL_PACKAGE_NAME}-image-image  += "/boot/*.dtb"

#kernel_do_compile_append() {
#	oe_runmake dtbs
#}

do_compile_append() {
	oe_runmake kernel.img
	cp ${S}/logo.bmp ${WORKDIR}/linux-nanopi_m4-standard-build/
	cp ${S}/logo_kernel.bmp ${WORKDIR}/linux-nanopi_m4-standard-build/
	${WORKDIR}/linux-nanopi_m4-standard-build/scripts/resource_tool --dtbname ${WORKDIR}/linux-nanopi_m4-standard-build/arch/arm64/boot/dts/rockchip/rk3399-nanopi4-rev*.dtb ${WORKDIR}/linux-nanopi_m4-standard-build/logo.bmp ${WORKDIR}/linux-nanopi_m4-standard-build/logo_kernel.bmp
}

do_install_append() {
	install -D -p -m0644 ${WORKDIR}/linux-nanopi_m4-standard-build/arch/arm64/boot/Image ${D}/boot/Image
#	install -D -p -m0644 ${WORKDIR}/linux-nanopi_m4-standard-build/arch/arm64/boot/dts/rockchip/rk3399-nanopi4-rev04.dtb ${D}/boot/rockchip/rk3399-nanopi4-rev04.dtb
#	install -D -p -m0644 ${WORKDIR}/linux-nanopi_m4-standard-build/arch/arm64/boot/dts/rockchip/rk3399-nanopi4-rev01.dtb ${D}/boot/rockchip/rk3399-nanopi4-rev01.dtb
#	install -D -p -m0644 ${WORKDIR}/linux-nanopi_m4-standard-build/arch/arm64/boot/dts/rockchip/rk3399-nanopi4-rev06.dtb ${D}/boot/rockchip/rk3399-nanopi4-rev06.dtb
#	install -D -p -m0644 ${WORKDIR}/linux-nanopi_m4-standard-build/arch/arm64/boot/dts/rockchip/rk3399-nanopi4-rev00.dtb ${D}/boot/rockchip/rk3399-nanopi4-rev00.dtb
}

do_deploy_append() {
	cp -a ${WORKDIR}/linux-nanopi_m4-standard-build/kernel.img ${DEPLOYDIR}
	cp -a ${WORKDIR}/linux-nanopi_m4-standard-build/resource.img ${DEPLOYDIR}
}
