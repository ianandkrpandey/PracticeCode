/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.pratik.kinesis.factory;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.model.Record;

/**
 * Processes records retrieved from stock trades stream.
 *
 */
public class RecordProcessor implements IRecordProcessor {

	private static final Log LOG = LogFactory.getLog(RecordProcessor.class);
	private String kinesisShardId;

	/**
	 * {@inheritDoc}
	 */
	public void initialize(String shardId) {
		LOG.info("Initializing record processor for shard: " + shardId);
		this.kinesisShardId = shardId;
	}

	/**
	 * {@inheritDoc}
	 */
	public void processRecords(List<Record> records, IRecordProcessorCheckpointer checkpointer) {
		System.out.println("Process record called at " + System.currentTimeMillis());
		for (Record record : records) {
			// process record
			byte[] b = record.getData().array();
			System.out.println("------------ record:" + Arrays.toString(b));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void shutdown(IRecordProcessorCheckpointer checkpointer, ShutdownReason reason) {
		LOG.info("Shutting down record processor for shard: " + kinesisShardId);
		// Important to checkpoint after reaching end of shard, so we can start
		// processing data from child shards.
	}
}
