<template>
  
</template>

<script>
import { listOrder } from "@/api/order/order"
export default {
    name: "Order",
    data() {
        return {
            orderList: []
        }
    },
    created() {
        this.getOrderList();
    },
    methods: {
        getOrderList() {
            listOrder().then(res => {
                console.log("11")
            })
        }
    }
}
</script>

<style>

</style>