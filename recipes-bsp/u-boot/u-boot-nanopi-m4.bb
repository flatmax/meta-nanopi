DEFAULT_PREFERENCE = "1"
include u-boot-nanopi-m4.inc

SRC_URI = " \
	git://github.com/u-boot/u-boot.git \
	file://001-drop-baudrate.patch \
	file://boot.cmd \
"
SRCREV = "50b4b80f597b9f59b98adbdbad691b1027bd501a"

S = "${WORKDIR}/git"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"
