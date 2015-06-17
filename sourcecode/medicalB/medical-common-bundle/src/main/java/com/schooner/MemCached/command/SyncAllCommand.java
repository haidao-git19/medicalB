/*******************************************************************************
 * Copyright (c) 2009 Schooner Information Technology, Inc.
 * All rights reserved.
 * 
 * http://www.schoonerinfotech.com/
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package com.schooner.MemCached.command;

import java.io.IOException;

import com.schooner.MemCached.SchoonerSockIO;
import com.netbull.shop.common.log.JLogger;
import com.netbull.shop.common.log.LoggerFactory;

/**
 * Sync all the data to flash. Only supported by Schooner memcached server.
 * 
 * @author Qinliang Lin
 * @see com.schooner.MemCached.command.SyncAllCommand
 * @since 2.5.0
 */
public class SyncAllCommand extends Command {

	// logger
	private static JLogger log = LoggerFactory.getLogger("biz.wss.cache");
	public static final String SYNCED = "SYNCED\r\n";

	public SyncAllCommand() {
		textLine = "sync_all\r\n".getBytes();
	}

	public boolean response(SchoonerSockIO sock, short rid) throws IOException {
		String line;
		byte[] temp = sock.getResponse(rid);
		sock.readBuf.get(temp);
		line = new String(temp);
		if (SYNCED.equals(line))
			return true;
		return false;
	}

}