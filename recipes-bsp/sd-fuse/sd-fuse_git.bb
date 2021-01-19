SUMMARY = "SD-FUSE RK3399"
DESCRIPTION = " Create bootable SD card for NanoPC-T4/NanoPi M4/NanoPi NEO4 for Yocto images"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/nishantpoorswani/sd-fuse-rk3399-yocto.git \
          file://001-set-partition-bootable.patch \
          "

S = "${WORKDIR}/git"

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}/out
    install -m 0755 ${S}/mk-sd-image.sh ${DEPLOYDIR}
    install -m 0755 ${S}/fusing.sh ${DEPLOYDIR}
    cp -rf ${S}/tools ${DEPLOYDIR}
    cp -rf ${S}/prebuilt ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile

do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_tar[noexec] = "1"
