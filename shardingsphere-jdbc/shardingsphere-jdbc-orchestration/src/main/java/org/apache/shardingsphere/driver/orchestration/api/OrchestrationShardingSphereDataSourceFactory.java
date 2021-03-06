/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.driver.orchestration.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.cluster.configuration.config.ClusterConfiguration;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.driver.orchestration.internal.datasource.OrchestrationShardingSphereDataSource;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.database.DefaultSchema;
import org.apache.shardingsphere.orchestration.center.config.OrchestrationConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Orchestration ShardingSphere data source factory.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrchestrationShardingSphereDataSourceFactory {
    
    /**
     * Create sharding data source.
     *
     * @param dataSourceMap data source map
     * @param ruleConfigurations rule configurations
     * @param orchestrationConfig orchestration configuration
     * @param props properties for data source
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final Map<String, DataSource> dataSourceMap, final Collection<RuleConfiguration> ruleConfigurations,
                                              final Properties props, final OrchestrationConfiguration orchestrationConfig) throws SQLException {
        if (null == ruleConfigurations || ruleConfigurations.isEmpty()) {
            return createDataSource(orchestrationConfig);
        }
        ShardingSphereDataSource shardingSphereDataSource = new ShardingSphereDataSource(dataSourceMap, ruleConfigurations, props);
        return new OrchestrationShardingSphereDataSource(shardingSphereDataSource, orchestrationConfig);
    }
    
    /**
     * Create sharding data source.
     *
     * @param dataSource data source
     * @param ruleConfigurations rule configurations
     * @param orchestrationConfig orchestration configuration
     * @param props properties for data source
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final DataSource dataSource, final Collection<RuleConfiguration> ruleConfigurations,
                                              final Properties props, final OrchestrationConfiguration orchestrationConfig) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>(1, 1);
        dataSourceMap.put(DefaultSchema.LOGIC_NAME, dataSource);
        return createDataSource(dataSourceMap, ruleConfigurations, props, orchestrationConfig);
    }
    
    /**
     * Create sharding data source.
     *
     * @param orchestrationConfig orchestration configuration
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final OrchestrationConfiguration orchestrationConfig) throws SQLException {
        return new OrchestrationShardingSphereDataSource(orchestrationConfig);
    }
    
    /**
     * Create sharding data source.
     *
     * @param dataSourceMap data source map
     * @param ruleConfigurations rule configurations
     * @param orchestrationConfig orchestration configuration
     * @param props properties for data source
     * @param clusterConfiguration cluster configuration
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final Map<String, DataSource> dataSourceMap, final Collection<RuleConfiguration> ruleConfigurations,
                                              final Properties props, final OrchestrationConfiguration orchestrationConfig, final ClusterConfiguration clusterConfiguration) throws SQLException {
        if (null == ruleConfigurations || ruleConfigurations.isEmpty()) {
            return createDataSource(orchestrationConfig, clusterConfiguration);
        }
        ShardingSphereDataSource shardingSphereDataSource = new ShardingSphereDataSource(dataSourceMap, ruleConfigurations, props);
        return new OrchestrationShardingSphereDataSource(shardingSphereDataSource, orchestrationConfig, clusterConfiguration);
    }
    
    /**
     * Create sharding data source.
     *
     * @param dataSource data source
     * @param ruleConfigurations rule configurations
     * @param orchestrationConfig orchestration configuration
     * @param props properties for data source
     * @param clusterConfiguration cluster configuration
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final DataSource dataSource, final Collection<RuleConfiguration> ruleConfigurations,
                                              final Properties props, final OrchestrationConfiguration orchestrationConfig, final ClusterConfiguration clusterConfiguration) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>(1, 1);
        dataSourceMap.put(DefaultSchema.LOGIC_NAME, dataSource);
        return createDataSource(dataSourceMap, ruleConfigurations, props, orchestrationConfig, clusterConfiguration);
    }
    
    /**
     * Create sharding data source.
     *
     * @param orchestrationConfig orchestration configuration
     * @param clusterConfiguration cluster configuration
     * @return sharding data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final OrchestrationConfiguration orchestrationConfig, final ClusterConfiguration clusterConfiguration) throws SQLException {
        return new OrchestrationShardingSphereDataSource(orchestrationConfig, clusterConfiguration);
    }
}
