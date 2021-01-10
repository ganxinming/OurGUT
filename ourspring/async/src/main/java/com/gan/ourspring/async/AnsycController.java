package com.gan.ourspring.async;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2020/12/6
 * @description
 */
@RestController
public class AnsycController {

	@Autowired
	AnsycService ansycService;

	@RequestMapping("/test")
	public String test() throws InterruptedException, ExecutionException {
		ansycService.startAnsycDiff();
		ansycService.startAnsyc();
		ansycService.startSynchronize();
		return "hello ansyc";
	}
}
