<template>
  
</template>

<script>
import { getMessageList } from "@/api/dataFlatform/message"
export default {
    name: "DataFlatform",
    data() {
        return {
            
        }
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            getMessageList().then(res => {
                console.log("22:",res)
            })
        }
    }

}
</script>

<style>

</style>