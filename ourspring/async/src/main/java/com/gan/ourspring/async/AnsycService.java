package com.gan.ourspring.async;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface AnsycService {

	Future<Map<Long, List<String>>> startAnsyc() throws InterruptedException;
	void startSynchronize() throws InterruptedException, ExecutionException;
	void startAnsycDiff() throws InterruptedException, ExecutionException;
}
