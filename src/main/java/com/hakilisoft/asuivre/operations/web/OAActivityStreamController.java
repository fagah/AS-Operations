package com.hakilisoft.asuivre.operations.web;

import com.hakilisoft.asuivre.operations.domain.OAActivityStream;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/aoactivitystreams")
@Controller
@RooWebScaffold(path = "aoactivitystreams", formBackingObject = OAActivityStream.class)
public class OAActivityStreamController {
}
