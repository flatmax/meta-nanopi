require recipes-bsp/u-boot/u-boot.inc
#require recipes-support/swupdate/swupdate.inc
inherit sfuchunk

DESCRIPTION = "Nanopi-M4 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS = "dtc-native bc-native swig-native python3-native lzop-native bison-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PARALLEL_MAKE = ""
# u-boot will build native python module
inherit pythonnative
HOST_EXTRACFLAGS += "-I${STAGING_INCDIR_NATIVE}"
EXTRA_OEMAKE += ' HOSTLDSHARED="${BUILD_CC} -shared ${BUILD_LDFLAGS} ${BUILD_CFLAGS}" '

#do_configure () {
#    if [ -z "${UBOOT_CONFIG}" ]; then
#      if [ -n "${UBOOT_MACHINE}" ]; then
#            oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
#        else
#            oe_runmake -C ${S} O=${B} oldconfig
#        fi
#        ${S}/scripts/kconfig/merge_config.sh -m .config ${@" ".join(find_cfgs(d))}
#        cml1_do_configure
#    fi
#}

do_compile_prepend () {
	export STAGING_INCDIR=${STAGING_INCDIR_NATIVE};
	export STAGING_LIBDIR=${STAGING_LIBDIR_NATIVE};
}

do_compile_append () {
	# copy to default search path
	if [ ${SPL_BINARY} ]; then
		cp ${B}/spl/${SPL_BINARY} ${B}/
	fi
	./tools/mkimage -C none -A arm -T script -d ../boot.cmd ${B}/../boot.scr
}

#do_install_append(){
#	install -D -p -m0644 ${B}/../boot.scr ${D}/boot/boot.scr
#}
