package com.aispace.erksystem.service.prometheus;

import com.sun.management.OperatingSystemMXBean;
import com.sun.management.UnixOperatingSystemMXBean;
import io.prometheus.client.Collector;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @author kangmoo Heo
 */
public class OsInfoExports extends Collector {
    OperatingSystemMXBean osBean;

    public OsInfoExports() {
        osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    }

    @Override
    public List<MetricFamilySamples> collect() {
        int os_available_processors = osBean.getAvailableProcessors();
        double os_system_load_average = osBean.getSystemLoadAverage();
        double os_system_cpu_load = osBean.getCpuLoad();
        double os_process_cpu_load = osBean.getProcessCpuLoad();
        long os_total_physical_memory_bytes = osBean.getTotalMemorySize();
        long os_committed_virtual_memory_bytes = osBean.getCommittedVirtualMemorySize();
        long os_free_physical_memory_bytes = osBean.getFreeMemorySize();
        //Unix 계열에서만 사용 가능
        long os_open_file_descriptor_count = 0;
        if (osBean instanceof UnixOperatingSystemMXBean unixBean) {
            os_open_file_descriptor_count = unixBean.getOpenFileDescriptorCount();
        }


        return List.of(
                new MetricFamilySamples(
                        "os_available_processors",
                        Type.GAUGE,
                        "Number of available processors",
                        List.of(new MetricFamilySamples.Sample(
                                "os_available_processors",
                                List.of(),
                                List.of(),
                                os_available_processors
                        ))
                ),
                new MetricFamilySamples(
                        "os_system_load_average",
                        Type.GAUGE,
                        "System load average over the last minute",
                        List.of(new MetricFamilySamples.Sample(
                                "os_system_load_average",
                                List.of(),
                                List.of(),
                                os_system_load_average
                        ))
                ),
                new MetricFamilySamples(
                        "os_system_cpu_load",
                        Type.GAUGE,
                        "System-wide CPU load percentage",
                        List.of(new MetricFamilySamples.Sample(
                                "os_system_cpu_load",
                                List.of(),
                                List.of(),
                                os_system_cpu_load
                        ))
                ),
                new MetricFamilySamples(
                        "os_process_cpu_load",
                        Type.GAUGE,
                        "Process-specific CPU load percentage",
                        List.of(new MetricFamilySamples.Sample(
                                "os_process_cpu_load",
                                List.of(),
                                List.of(),
                                os_process_cpu_load
                        ))
                ),
                new MetricFamilySamples(
                        "os_total_physical_memory_bytes",
                        Type.GAUGE,
                        "Total physical memory size in bytes",
                        List.of(new MetricFamilySamples.Sample(
                                "os_total_physical_memory_bytes",
                                List.of(),
                                List.of(),
                                os_total_physical_memory_bytes
                        ))
                ),
                new MetricFamilySamples(
                        "os_committed_virtual_memory_bytes",
                        Type.GAUGE,
                        "Total committed virtual memory size in bytes",
                        List.of(new MetricFamilySamples.Sample(
                                "os_committed_virtual_memory_bytes",
                                List.of(),
                                List.of(),
                                os_committed_virtual_memory_bytes
                        ))
                ),
                new MetricFamilySamples(
                        "os_free_physical_memory_bytes",
                        Type.GAUGE,
                        "Free physical memory size in bytes",
                        List.of(new MetricFamilySamples.Sample(
                                "os_free_physical_memory_bytes",
                                List.of(),
                                List.of(),
                                os_free_physical_memory_bytes
                        ))
                ),
                new MetricFamilySamples(
                        "os_open_file_descriptor_count",
                        Type.GAUGE,
                        "Number of open file descriptors",
                        List.of(new MetricFamilySamples.Sample(
                                "os_open_file_descriptor_count",
                                List.of(),
                                List.of(),
                                os_open_file_descriptor_count
                        ))
                )
        );
    }
}
