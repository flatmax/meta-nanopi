#echo bootargs
#setenv bootargs "root=/dev/mmcblk0p1 earlyprintk console=ttyS2,115200n8 rw rootwait"
# bootargs aren't used with yocto, set the "APPEND" variable in the machine conf
echo loading devicetree
ext4load mmc ${devnum}:${distro_bootpart} ${fdt_addr_r} /boot/rk3399-nanopi4-rev01.dtb
echo loading kernel
ext4load mmc ${devnum}:${distro_bootpart} ${kernel_addr_r} /boot/Image
echo $bootargs
echo booting ...
booti ${kernel_addr_r} - ${fdt_addr_r}
