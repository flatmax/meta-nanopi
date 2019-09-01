inherit image_types

# Use an uncompressed ext4 by default as rootfs
IMG_ROOTFS_TYPE = "ext4"
IMG_ROOTFS = "${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.${IMG_ROOTFS_TYPE}"
# This image depends on the rootfs image
IMAGE_TYPEDEP_nanopim4-gpt-img = "${IMG_ROOTFS_TYPE}"
DDR_BIN = "ddr.bin"
MINILOADER_BIN = "miniloader.bin"
IDBLOADER = "idbloader.img"

do_image_nanopim4_gpt_img[depends] += " \
	nanopi-rk3399-binary-loader:do_populate_lic \
	sd-fuse:do_populate_lic \
	virtual/bootloader:do_populate_lic"

do_image_nanopim4_gpt_img[depends] += " \
	parted-native:do_populate_sysroot \
	u-boot-mkimage-native:do_populate_sysroot \
	nanopi-rk3399-binary-native:do_populate_sysroot \
	mtools-native:do_populate_sysroot \
	gptfdisk-native:do_populate_sysroot \
	dosfstools-native:do_populate_sysroot \
	nanopi-rk3399-binary-loader:do_deploy \
	sd-fuse:do_deploy \
	virtual/kernel:do_deploy \
	virtual/bootloader:do_deploy"

IMAGE_CMD_nanopim4-gpt-img () {
	# Change to image directory
	cd ${DEPLOY_DIR_IMAGE}

	# Remove the existing image
	rm -rf "yocto"

	create_nanopi_m4_image

}

create_nanopi_m4_image () {
	mkdir -p ${DEPLOY_DIR_IMAGE}/yocto
	loaderimage --pack --uboot ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin ${DEPLOY_DIR_IMAGE}/yocto/uboot.img 0x200000
	mkimage -n rk3399 -T rksd -d ${DEPLOY_DIR_IMAGE}/${DDR_BIN} ${DEPLOY_DIR_IMAGE}/yocto/${IDBLOADER}
	cat ${DEPLOY_DIR_IMAGE}/${MINILOADER_BIN} >>${DEPLOY_DIR_IMAGE}/yocto/${IDBLOADER}
	cp -rf kernel.img ${DEPLOY_DIR_IMAGE}/yocto
	cp -rf resource.img ${DEPLOY_DIR_IMAGE}/yocto
	cp -rf prebuilt/trust.img ${DEPLOY_DIR_IMAGE}/yocto
	cp -rf prebuilt/boot.img ${DEPLOY_DIR_IMAGE}/yocto
	cp -rf prebuilt/generic/partmap.txt ${DEPLOY_DIR_IMAGE}/yocto
	cp -rf prebuilt/generic/param4sd.txt ${DEPLOY_DIR_IMAGE}/yocto   
	cp -rL ${IMG_ROOTFS} ${DEPLOY_DIR_IMAGE}/yocto/rootfs.img
}

