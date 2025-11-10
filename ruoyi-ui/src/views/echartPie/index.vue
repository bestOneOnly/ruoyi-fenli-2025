<template>
    <div>

        <div id="main" style="width: 600px;height:400px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    name: "EchartPie",
    data() {
        return {
            pieEchart: null
        }
    },
    created() {

    },
    mounted() {
        // 指定图表的配置项和数据
        if (!this.pieEchart) {
            this.myChart = echarts.init(document.getElementById('main'));
        }
        let option = this.pieEchartInit();
        this.myChart.setOption(option);
        this.myChart.on('click', function (params) {
            console.log(params);
        });
    },
    methods: {
        init(){

        },
        pieEchartInit() {
            let option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 20,
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            { value: 1048, name: 'Search Engine' },
                            { value: 735, name: 'Direct' },
                            { value: 580, name: 'Email' },
                            { value: 484, name: 'Union Ads' },
                            { value: 300, name: 'Video Ads' }
                        ]
                    }
                ]
            };
            return option;
        }
    }
}
</script>

<style></style>