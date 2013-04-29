package com.hakilisoft.asuivre.operations.web;

import com.hakilisoft.asuivre.operations.domain.OAUser;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/oausers")
@Controller
@RooWebScaffold(path = "oausers", formBackingObject = OAUser.class)
public class OAUserController {
}
