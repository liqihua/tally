<template>
  <div class="page-pay-log-list" v-loading.fullscreen.lock="loading">

    <el-header>
      <div class="div-input-wrap">
        <el-input v-model="total" class="log-input input-total" type="number" placeholder="金额/元" :label-width="50"></el-input>
        <el-input v-model="productName" class="log-input input-product-name" type="text" placeholder="商品名称" ></el-input>
        <el-select v-model="productType" class="log-input" filterable placeholder="商品分类">
          <el-option v-for="item in productTypeArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-date-picker v-model="payTime" type="date" placeholder="消费时间" value-format="yyyy-MM-dd"></el-date-picker>
        <el-button type="primary" icon="el-icon-success" @click="addLog()">save</el-button>
      </div>
    </el-header>


    <el-main>


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


    </el-main>
  </div>
</template>


<script>
  import API,{productType} from '@/config/config';
  import {accMul,saveLog,getList} from '@/js/common.js';
  import axios from 'axios';

  export default {
    name: 'address_list',
    data() {
      return {
        total:0,
        productName:'',
        productType:'',
        payTime:'',
        productTypeArr:productType,

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
        edit_payTime:''
      }
    },
    created:function(){
      getList(this);
    },
    methods:{
      addLog(){
        var data = {
          total : this.total,
          productName : this.productName,
          productType : this.productType,
          payTime : this.payTime
        }
        saveLog(data,this);
      },
      editLog(log){
        this.centerDialogVisible = true;
        this.edit_id = log.id;
        this.edit_total = log.total;
        this.edit_productName = log.productName;
        this.edit_productType = log.productType;
        this.edit_payTime = log.payTime;
      },
      doEdit(){
        var data = {
          logId : this.edit_id,
          total : this.edit_total,
          productName : this.edit_productName,
          productType : this.edit_productType,
          payTime : this.edit_payTime
        }
        var success = saveLog(data,this);
        if(success){
          getList(this);
        }
        this.centerDialogVisible = false;
      },
      deleteLog(log) {
        var userId = localStorage.getItem("userId");
        var token = localStorage.getItem("token");
        if(userId == null || userId == '' || token == null || token == ''){
          this.$router.push("/login");
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
              getList(that);
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
        });
      },
      pageClick(clickPage){
        if(clickPage != this.page){
          this.page = clickPage;
          getList(this);
        }
      }
    }
  }







</script>


<style scoped>
  .page-pay-log-list{width: 100%;}
  .el-header{margin-top: 20px;}
  .div-input-wrap{width:100%;height:100%;}
  .log-input{max-width: 200px;}

  .page-pay-log-list .el-table{ margin-top: 10px;}
  .page-pay-log-list .el-pagination{ margin-top: 20px;}
  .edit-input-wrap el-input{ max-width: 200px;}
</style>
