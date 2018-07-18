<template>
  <div class="page-pay-log-list">

    <!-- tab -->
    <el-tabs type="card">
      <el-tab-pane label="支出记录"></el-tab-pane>
      <!--<el-tab-pane label="地址添加"></el-tab-pane>-->
    </el-tabs>

    <!-- 列表 -->
    <el-table :data="tableData" border style="width: 100%" >
      <el-table-column prop="total" label="金额/元" width="180" align="center"></el-table-column>
      <el-table-column prop="productName" label="商品名称" width="180" align="center"></el-table-column>
      <el-table-column prop="productType" label="消费类型" align="center"></el-table-column>
      <el-table-column prop="payTime" label="消费时间" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="small" icon="el-icon-edit" @click="centerDialogVisible = true">edit</el-button>
          <el-button type="danger" size="small" icon="el-icon-delete">delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination background layout="prev, pager, next" :total="1000"></el-pagination>

    <!-- 编辑框弹出框 -->
    <el-dialog title="edit log" :visible.sync="centerDialogVisible" width="300px" center>
      <div class="edit-input-wrap">
        <el-input class="input-total" type="number" placeholder="金额/元" ></el-input><br><br>
        <el-input class="input-product-name" type="text" placeholder="商品名称" ></el-input><br><br>
        <el-input class="input-product-type" type="text" placeholder="消费分类" ></el-input><br><br>
        <el-date-picker v-model="payTime" type="date" placeholder="消费时间"></el-date-picker><br><br>
      </div>
      <div class="edit-dialog-footer">
        <el-button @click="centerDialogVisible = false">cancel</el-button>
        <el-button type="primary" @click="centerDialogVisible = false" icon="el-icon-success">save</el-button>
      </div>
    </el-dialog>

  </div>
</template>


<script>
  import API from '@/config/config';
  import axios from 'axios';
  import store from '@/store/store.js';

  export default {
    name: 'address_list',
    store,
    computed:{
      updateLogList(){
        return this.$store.state.updateLogList;
      }
    },
    watch:{
      updateLogList:function(){
        getList(this);
      }
    },
    data() {
      return {
        page:0,
        pageSize:10,
        payTime:'',
        centerDialogVisible: false,
        tableData: []
      }
    },
    created:function(){
      getList(this);
    }
  }


  function getList(that){
    var userId = localStorage.getItem("userId");
    var token = localStorage.getItem("token");
    if(userId == null || userId == '' || token == null || token == ''){
      location.href = "/";
    }
    axios({
      method: 'get',
      url: API.GET_LOG_LIST+"?userId="+userId+"&token="+token+"&page="+that.page+"&pageSize="+that.pageSize,
    }).then(function (res) {
      console.log(res);
      that.loading = false;
      if(res.data.resultCode == 10000){
        for(var i=0;i<res.data.resultData.list.length;i++){
          res.data.resultData.list[i].payTime = res.data.resultData.list[i].payTime.substring(0,10);
        }
        that.tableData = res.data.resultData.list;
      }else{
        if(res.data.resultCode == 42003){
          location.href = "/";
        }
        that.$message.error({
          message: res.data.resultMessage,
          center: true
        });
      }
    }).catch(function (res) {
      console.log(res);
      that.loading = false;
    });
  }
</script>


<style scoped>
  .page-pay-log-list .el-table{ margin-top: 10px;}
  .page-pay-log-list .el-pagination{ margin-top: 20px;}
  .edit-input-wrap el-input{ max-width: 200px;}
</style>
