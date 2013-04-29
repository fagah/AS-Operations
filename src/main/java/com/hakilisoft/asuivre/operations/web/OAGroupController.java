package com.hakilisoft.asuivre.operations.web;

import com.hakilisoft.asuivre.operations.domain.OAGroup;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/oagroups")
@Controller
@RooWebScaffold(path = "oagroups", formBackingObject = OAGroup.class)
public class OAGroupController {
}
