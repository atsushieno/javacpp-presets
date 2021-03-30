package org.bytedeco.libremidi.presets;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.*;

@Properties(
    inherit = javacpp.class,
    target = "org.bytedeco.libremidi",
    global = "org.bytedeco.libremidi.global.LibreMidi",
    value = {
        @Platform(
            value = {"linux-x86", "linux-x86_64"},
            include = { "libremidi.hpp" },
            link = "asound",
            preload = "libasound"
        )

    }
)
public class LibreMidi implements InfoMapper {
    static { Loader.checkVersion("org.bytedeco", "libremidi"); }

    public void map(InfoMap infoMap) {
        infoMap
            .put(new Info("LIBREMIDI_HAS_SPAN").define(false))

            .put(new Info("std::function<void(midi_errortype,std::string_viewerrorText)>").pointerTypes("FunctionPointer"))
            .put(new Info("std::optional<libremidi::chunking_parameters>").pointerTypes("Pointer"))

            .put(new Info("std::function<void(int,std::string)>").pointerTypes("FunctionPointer"))

            .put(new Info("std::function<bool(std::chrono::microseconds,int)>").pointerTypes("FunctionPointer"))

            .put(new Info("auto").skip())

            //.put(new Info("pollfd").pointerTypes("pollfdHandle"))
            //.put(new Info("pollfdHandle").valueTypes("pollfdHandle").pointerTypes("@Cast(\"pollfdHandle*\") PointerPointer", "@ByPtrPtr pollfdHandle"))
            ;
    }
}
