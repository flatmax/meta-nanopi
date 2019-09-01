DESCRIPTION = "Rockchip binary loader"

LICENSE = "BINARY"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"
NO_GENERIC_LICENSE[BINARY] = "LICENSE.TXT"

DEPENDS = "nanopi-rk3399-binary-native"

SRC_URI = "git://github.com/armbian/rkbin.git;branch=master"
SRCREV = "90dbe9aa6e98bc49405f7c04722f08a613029b13"
S = "${WORKDIR}/git"

inherit deploy

RK_BIN_FOLDER ?= "rk33"

RK_DDR_BIN ?= "rk3399_ddr_800MHz_v1.14.bin"
RK_MINILOADER_BIN ?= "rk3399_miniloader_v1.15.bin"

do_deploy() {
    install -m 0644 ${S}/${RK_BIN_FOLDER}/${RK_DDR_BIN} ${DEPLOYDIR}/ddr.bin
    install -m 0644 ${S}/${RK_BIN_FOLDER}/${RK_MINILOADER_BIN} ${DEPLOYDIR}/miniloader.bin
    install -m 0644 ${S}/img/rk3399/trust.img ${DEPLOYDIR}/trust.img
}

addtask deploy before do_build after do_compile

do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_tar[noexec] = "1"
