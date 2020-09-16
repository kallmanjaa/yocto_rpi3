# Hobby/Learning
    Goal is to use only POKY Yocto layer and create rpi meta-layer on own
    End result, Rpi3 should be able to boot

# Notes:
    The rpi image is based on core-image-minimal 

# Kernel:
    used kernel version is 5.5y

# Image file
    wic file need to be flashed to sdcard using dd command or your favorite tools like rufus
    Image file: <path-to-src>/build/tmp/deploy/images/rpi3/rpi3-image-rpi3.wic

# Next steps/TODO:
    Extend this layer to use QT or any application that should run on rpi by adding recipies

# Get started
## Docker
    used docker to replicate same build env everywhere
    ubuntu 18.04
    docker build -t kalmanjaa_yocto .
    docker run --rm --user=kalmanjaa --name test_yocto -v ${PWD}/../yocto_rpi3:/yocto_rpi3 -it kalmanjaa_yocto /bin/bash
    
## Usage 
    In the docker env,
    just run ./build.sh, everything is taken care
    
## Reference
    https://git.yoctoproject.org/git/meta-raspberrypi

## Adding actions-runner
