package com.hakilisoft.asuivre.operations.web;

import com.hakilisoft.asuivre.operations.domain.OATenant;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/oatenants")
@Controller
@RooWebScaffold(path = "oatenants", formBackingObject = OATenant.class)
public class OATenantController {
}
