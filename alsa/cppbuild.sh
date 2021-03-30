#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" alsa
    popd
    exit
fi

case $PLATFORM in
    linux-*)
        if [[ ! -d "/usr/include/alsa/" ]]; then
            echo "/usr/include/alsa/ directory does not exist."
            exit 1
        fi
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        exit 1
        ;;
esac
