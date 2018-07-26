<template>
  <div class="page-pay-log-list" v-loading.fullscreen.lock="loading">

    <!-- tab -->
    <el-tabs type="card">
      <el-tab-pane label="支出记录"></el-tab-pane>
    </el-tabs>

    <!-- 列表 -->
    <el-table :data="tableData" border style="width: 100%" >
      <el-table-column prop="total" label="金额/元" width="180" align="center"></el-table-column>
      <el-table-column prop="productName" label="商品名称" width="180" align="center"></el-table-column>
      <el-table-column prop="productType" label="消费类型" align="center"></el-table-column>
      <el-table-column prop="payTime" label="消费时间" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="small" icon="el-icon-edit" @click="editLog(scope.row)">edit</el-button>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="deleteLog(scope.row)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination background layout="prev, pager, next" :total="dataCount" :page-size="pageSize" :current-page="page" @current-change="pageClick" ></el-pagination>

    <!-- 编辑框弹出框 -->
    <el-dialog title="edit log" :visible.sync="centerDialogVisible" width="300px" center>
      <div class="edit-input-wrap">
        <el-input v-model="edit_total" class="input-total" type="number" placeholder="金额/元"></el-input><br><br>
        <el-input  v-model="edit_productName" class="input-product-name" type="text" placeholder="商品名称"></el-input><br><br>
        <el-select v-model="edit_productType" filterable placeholder="商品分类">
          <el-option v-for="item in productTypeArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select><br><br>
        <el-date-picker v-model="edit_payTime" type="date" placeholder="消费时间" value-format="yyyy-MM-dd"></el-date-picker><br><br>
      </div>
      <div class="edit-dialog-footer">
        <el-button @click="centerDialogVisible = false">cancel</el-button>
        <el-button type="primary" @click="doEdit()" icon="el-icon-success">save</el-button>
      </div>
    </el-dialog>

  </div>
</template>


<script>
  import API,{productType} from '@/config/config';
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
        loading:false,
        page:1,
        pageSize:10,
        centerDialogVisible: false,
        tableData: [],
        dataCount:0,
        edit_id:0,
        edit_total:0,
        edit_productName:'',
        edit_productType:'',
        edit_payTime:'',
        productTypeArr:productType
      }
    },
    created:function(){
      getList(this);
    },
    methods:{
      pageClick(clickPage){
        if(clickPage != this.page){
          this.page = clickPage;
          getList(this);
        }
      },
      editLog(log){
        console.log(log);
        this.centerDialogVisible = true;
        this.edit_id = log.id;
        this.edit_total = log.total;
        this.edit_productName = log.productName;
        this.edit_productType = log.productType;
        this.edit_payTime = log.payTime;
      },
      doEdit(){
        if(this.edit_total == null || this.edit_total == ''){
          alert("金额不能为空");return;
        }
        if(this.edit_total <= 0){
          alert("金额必须大于0");return;
        }
        if(this.edit_productName == null || this.edit_productName == ''){
          alert("商品名称不能为空");return;
        }
        if(this.edit_productType == null || this.edit_productType == ''){
          alert("商品分类不能为空");return;
        }
        if(this.edit_payTime == null || this.edit_payTime == ''){
          alert("消费时间不能为空");return;
        }
        var userId = localStorage.getItem("userId");
        var token = localStorage.getItem("token");
        if(userId == null || userId == '' || token == null || token == ''){
          location.href = "/";
        }
        var that = this;
        that.loading = true;
        axios({
          method: 'post',
          url: API.SAVE_LOG_PAY,
          headers : {
            "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
          },
          data: "logId="+this.edit_id+"&total="+(parseInt(this.edit_total*100))+"&productName="+this.edit_productName+"&productType="+this.edit_productType+"&payTime="+this.edit_payTime+"&userId="+userId+"&token="+token
        }).then(function (res) {
          console.log(res);
          that.loading = false;
          if(res.data.resultCode == 10000){
            that.centerDialogVisible = false;
            that.$message({
              message:'保存成功',
              center:true,
              type:'success'
            });
            store.commit('doUpdateLogList');
          }else{
            that.$message.error({
              message: res.data.resultMessage,
              center: true
            });
          }
        }).catch(function (res) {
          console.log(res);
          that.loading = false;
        });
      },
      deleteLog(log) {
        var userId = localStorage.getItem("userId");
        var token = localStorage.getItem("token");
        if(userId == null || userId == '' || token == null || token == ''){
          location.href = "/";
        }
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var that = this;
          that.loading = true;
          axios({
            method: 'post',
            url: API.DELETE_PAY_LOG,
            headers : {
              "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
            },
            data: "logId="+log.id+"&userId="+userId+"&token="+token
          }).then(function (res) {
            console.log(res);
            that.loading = false;
            if(res.data.resultCode == 10000){
              that.centerDialogVisible = false;
              that.$message({
                message:'删除成功',
                center:true,
                type:'success'
              });
              store.commit('doUpdateLogList');
            }else{
              that.$message.error({
                message: res.data.resultMessage,
                center: true
              });
            }
          }).catch(function (res) {
            console.log(res);
            that.loading = false;
          });

          /*this.$message({
            type: 'success',
            message: '删除成功!'
          });*/
        });
      }
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
      url: API.GET_LOG_LIST+"?userId="+userId+"&token="+token+"&page="+(that.page - 1)+"&pageSize="+that.pageSize,
    }).then(function (res) {
      console.log(res);
      that.loading = false;
      if(res.data.resultCode == 10000){
        for(var i=0;i<res.data.resultData.list.length;i++){
          res.data.resultData.list[i].payTime = res.data.resultData.list[i].payTime.substring(0,10);
        }
        for(var i=0;i<res.data.resultData.list.length;i++){
          res.data.resultData.list[i].total = res.data.resultData.list[i].total / 100;
        }
        that.tableData = res.data.resultData.list;
        console.log(that.tableData);
        that.dataCount = res.data.resultData.count;
        that.page = res.data.resultData.page + 1;
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
