# Yocto / OpenEmbedded support for FriendlyARM NanoPi and NanopPi-M4

Introduction
-------------------------

The meta-nanopi layer depends on:

	URI: git://git.openembedded.org/openembedded-core
	layers: meta
	branch: master

## Nanopi-M4

If your chosen machine is Nanopi-m4, the image will be present in tmp/deploy/images/nanopi-m4. You can now generate a bootable sd-card image or flash to sd-card directly with the help of the fusing scripts

1) Generating bootable sd-card images

Run the following command in tmp/deploy/images/nanopi-m4:

```
sudo ./mk-sd-image.sh yocto
```

This will create a sd-card image in the tmp/deploy/images/nanopi-m4/out folder called as rk3399-sd-yocto-linux-4.4-arm64-$(date +%Y%m%d).img

2) Flashing the image directly to the sd-card using fusing scripts

Run the following command in tmp/deploy/images/nanopi-m4 after inserting the sd-card:

```
sudo ./fusing.sh /dev/sdX yocto
``` 

**Please replace x in /dev/sdx with your device(eg /dev/sdb)** 
