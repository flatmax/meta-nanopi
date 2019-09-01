SUMMARY = "Nanopi-M4 firmware"
DESCRIPTION = "Rockchip firmware such as for the WIFI, BT"

LICENSE = "proprietary-binary"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=b8a889d397e2a3ed8bc9413d0698947e"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/nishantpoorswani/nanopi-m4-bin.git"
S = "${WORKDIR}/git"

do_install () {
	install -d ${D}/system/etc/firmware/
	install -d ${D}/system/vendor/firmware/
	cp -rf ${S}/system/* ${D}/system/
	install -d ${D}/etc/firmware/
	cp -rf ${S}/etc/* ${D}/etc/
	install -d ${D}/lib/firmware/
	cp -rf ${S}/lib/* ${D}/lib/
	install -d ${D}/usr/bin/
	cp -rf ${S}/usr/* ${D}/usr/
	mv ${D}/usr/bin/brcm_patchram_plus1_64 ${D}/usr/bin/brcm_patchram_plus1
	mv ${D}/usr/bin/rk_wifi_init_64 ${D}/usr/bin/rk_wifi_init
	rm -rf ${D}/usr/bin/rk_wifi_init_32 
	rm -rf ${D}/usr/bin/brcm_patchram_plus1_32 
}

FILES_${PN} = "/system/* \
  	/etc/firmware/* \
  	/lib/firmware/* \
	/usr/bin/brcm_patchram_plus1 \
	/usr/bin/rk_wifi_init \
	/usr/bin/rtk_hciattach \	
"
INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
do_package_qa[noexec] = "1"
