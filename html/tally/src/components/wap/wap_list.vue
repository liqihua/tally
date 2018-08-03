<template>
  <div id="wap_list" v-loading.fullscreen.lock="loading">
    <el-table :data="tableData" style="width: 100%;">
      <el-table-column label="金额/元" align="center">
        <template slot-scope="scope">
          <span class="span-total">{{scope.row.total}}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品详情" align="center">
        <template slot-scope="scope">
          {{scope.row.productName}}<br>
          {{scope.row.productType}}<br>
          {{scope.row.payTime}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <div class="wrap-btn">
          <el-button type="primary" size="small" icon="el-icon-edit" @click="editLog(scope.row)"></el-button>
          </div>
          <div class="wrap-btn">
          <el-button type="danger" size="small" icon="el-icon-delete" @click="showDeleteDialog(scope.row)"></el-button>
          </div>
        </template>
      </el-table-column>

    </el-table>

    <!-- 删除提醒框 -->
    <el-dialog title="确定删除？" :visible.sync="dialogDeleteVisible">
      <el-button @click="dialogDeleteVisible = false" size="mini">取 消</el-button>
      <el-button type="primary" @click="deleteLog()" size="mini">确 定</el-button>
    </el-dialog>

    <!-- 编辑框弹出框 -->
    <el-dialog title="edit log" :visible.sync="dialogEditVisible" width="300px" center>
      <div class="edit-input-wrap">
        <el-input v-model="edit_total" class="input-total" type="number" placeholder="金额/元"></el-input><br><br>
        <el-input  v-model="edit_productName" class="input-product-name" type="text" placeholder="商品名称"></el-input><br><br>
        <el-select v-model="edit_productType" filterable placeholder="商品分类">
          <el-option v-for="item in productTypeArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select><br><br>
        <el-date-picker v-model="edit_payTime" type="date" placeholder="消费时间" value-format="yyyy-MM-dd"></el-date-picker><br><br>
      </div>
      <div class="edit-dialog-footer">
        <el-button @click="dialogEditVisible = false">cancel</el-button>
        <el-button type="primary" @click="doEdit()" icon="el-icon-success">save</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import API,{productType} from '@/config/config';
  import axios from 'axios';
  import {saveLog,getListWAP} from '@/js/common.js';
  import $ from 'jquery';


  $(function () {
    $(window).scroll(function(){
      let scrollTop = $(this).scrollTop();
      let scrollHeight = $(document).height();
      let windowHeight = $(this).height();
      if(scrollTop + windowHeight === scrollHeight){
        console.log(111);
      }else{
        console.log(222);
      }

    });
  })

  export default {
    name: 'wap_list',
    data() {
      return {
        loading:false,
        page:1,
        pageSize:10,
        tableData: [],

        dialogDeleteVisible:false,
        deleteId:'',

        dialogEditVisible:false,
        productTypeArr:productType,
        edit_id:0,
        edit_total:0,
        edit_productName:'',
        edit_productType:'',
        edit_payTime:'',

        page:1,
        pageSize:10,
      }
    },
    created:function(){
      getListWAP(this);
      //判断滚动条是否滚动到底部
      /*window.addEventListener('scroll',function(){
        if(document.body.scrollTop + window.innerHeight >= document.body.offsetHeight) {
          alert(1);
        }
      });*/
      $(window).scroll(function(){
        let scrollTop = $(this).scrollTop();
        let scrollHeight = $(document).height();
        let windowHeight = $(this).height();
        if(scrollTop + windowHeight === scrollHeight){
          console.log(111);
        }else{
          console.log(222);
        }
      });
    },
    methods:{
      openDatePicker(){
        this.$refs.datePicker.open();
      },
      datePickerConfirm(){
        this.payTime = this.payTimePicker.format('yyyy-MM-dd');
      },
      showDeleteDialog(log){
        this.deleteId = log.id;
        this.dialogDeleteVisible = true;
      },
      deleteLog() {
        if(this.deleteId != null && this.deleteId != ''){
          this.dialogDeleteVisible = false;

          var userId = localStorage.getItem("userId");
          var token = localStorage.getItem("token");
          if(userId == null || userId == '' || token == null || token == ''){
            this.$router.push("/wap/login");
          }
          var that = this;
          that.loading = true;
          axios({
            method: 'post',
            url: API.DELETE_PAY_LOG,
            headers : {
              "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
            },
            data: "logId="+this.deleteId+"&userId="+userId+"&token="+token
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
              getListWAP(that);
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
        }
      },
      editLog(log){
        this.dialogEditVisible = true;
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
        saveLog(data,this,"/wap/login",true);
        this.dialogEditVisible = false;
      },

    }
  }




</script>

<style scoped>
  #wap_list,.el-table{width: 100%}
  .wrap-btn,.wrap-btn>.el-button{width: 100%;}
  .wrap-btn:not(:first-child){
    margin-top: 0.5rem;
  }
  .span-total{font-size: 1.5rem;}
</style>
