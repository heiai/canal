package com.alibaba.otter.canal.parse.index;

import com.alibaba.otter.canal.common.CanalLifeCycle;
import com.alibaba.otter.canal.parse.exception.CanalParseException;
import com.alibaba.otter.canal.protocol.position.LogPosition;

/**
 * 记录binlog最后一次解析成功位置信息，主要是描述下一次canal启动的位点
 *
 * 1.如果CanalEventStore选择的是内存模式，可不保留解析位置，下一次canal启动时直接依赖CanalMetaManager记录的最后一次消费成功的位点即可.(最后一次ack提交的数据位点)
 * 2.如果CanalEventStore选择的是持久化模式，可通过zookeeper记录位点信息，canal instance发生failover切换到另一台机器，可通过读取zookeeper获取位点信息
 * Created by yinxiu on 17/3/17. Email: marklin.hz@gmail.com
 */
public interface CanalLogPositionManager extends CanalLifeCycle {

    LogPosition getLatestIndexBy(String destination);

    void persistLogPosition(String destination, LogPosition logPosition) throws CanalParseException;

}
