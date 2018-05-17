<template>
<div style="width:100%;height:100%; margin-top:6%">
<div style="display:inline">
    <form novalidate class="md-layout" @submit.prevent="validateUser"> 
      <md-card class="md-layout-item md-size-50 md-small-size-100" style="display:inline-block">
        <md-card-header>
            <div class="md-title">원고 작성</div>
        </md-card-header>
        <md-card-header>
        </md-card-header>
        <md-card-header>
          <md-field >
            <label for="title">Title</label>
            <md-input type="text" name="title" id="title" autocomplete="title" v-model="imageUploadForm.title"/>
          </md-field>
           <md-field >
            <label for="title">Summary</label>
            <md-input type="text" name="title" id="title" autocomplete="title" v-model="imageUploadForm.oneWorld"/>
          </md-field>
          
        <md-card-header>
           <md-button class="md-raised" @click="$refs.thumbnailInput.click()">Thumbnail</md-button>
           <label>Thumbnail 최대 크기 1MB</label>
            <input type="file" style="display:none" ref="thumbnailInput" @change="thumbnailImage">
            
        </md-card-header>
            <Img :src="thumbnail" v-bind:class="{active : is}"/>
        </md-card-header>
      </md-card>
      
      <md-card class="md-layout-item md-size-50 md-small-size-100" style="display:inline-block">
         <md-card-header>
        </md-card-header>
        
        <md-card-header>
          <md-button class="md-raised" @click="$refs.fileInput.click()">WebtoonSelect</md-button>
            <input type="file" style="display:none" multiple ref="fileInput" @change="selectImage">
            <md-button class="md-raised" @click="cancel">Cancel</md-button>
            <label>원고 최대 크기 10MB</label>
        </md-card-header>
       <md-card-header>
        
            <md-content class="md-scrollbar"> 
            <md-list v-for="(filename,index) in selectList">
            <md-list-item>{{filename}}</md-list-item>
            </md-list>
            </md-content>
            </md-card-header>
        <md-card-header>
            <md-card-header>
                <md-button style="float:right" class="md-raised" @click="submit">저장</md-button>   
            </md-card-header>     
        </md-card-header>
      </md-card>
    </form>
</div>
</div>
</template>

<script>
export default {
  name: "CreateManuscript",
  data() {
    return {
      is:false,
      fileList: [],
      selectList: [],
      size: "",
      thumbnail: "",
      thumbImage:'',
      uid: 5,
      imageUploadForm: {
        userId: "",
        title: "",
        oneWorld: "",
        round: 1
      },
      form: new FormData()
    };
  },
  methods: {
    selectImage(e) {
      let files = e.target.files;
      if (!files.length) {
        return false;
      }

      for (let a = 0; a < files.length; a++) {
        console.log(files[0].size);
        this.selectList.push(files[a].name);
      }
      for (let a = 0; a < files.length; a++) {
        this.fileList.push(files[a]);
      }
    },
    thumbnailImage(e) {
      let files = e.target.files || e.dataTransfer.files;
      this.thumbImage=files[0]
      console.log(files);
      if (!files.length) return;
      this.createImage(files[0]);
      this.is=true;
    },
    createImage(file) {
      let image = new Image();
      let reader = new FileReader();
      let vm = this;

      reader.onload = e => {
        vm.thumbnail = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    cancel(){
      const input = this.$refs.fileInput
      input.type = 'text'
      input.type = 'file'
      this.fileList=[]
      this.selectList=[]
    },
    submit: async function name() {
      if (!this.imageUploadForm.title) {
        alert("제목을 입력하세요");
        return false;
      }
      if (!this.imageUploadForm.oneWorld) {
        alert("한마디 입력하세요");
        return false;
      }
      if (!this.thumbImage) {
        alert('썸네일을 선택해주세요');
        return false;
      }
      if (!this.fileList.length) {
        alert('이미지를 선택해주세요');
        return false;
      }
      for (let a = 0; a < this.fileList.length; a++) {
        this.form.append('files', this.fileList[a]);
      }
      this.imageUploadForm.userId = this.uid;
      this.form.append("thumb",this.thumbImage);
      this.form.append(
        'manuscript',
        new Blob([JSON.stringify(this.imageUploadForm)], {
          type: 'application/json'
        })
      );
        this.$http.post('/api/images/upload', this.form, 'Content-type : undefined')
        .then((result) => {
            console.log(result)
            if(result.body.statusCode=='THUMBNAILWRITE'){
                this.form=new FormData();
                alert("썸네일 파일 크기가 너무 큽니다.")
            }
            if(result.body.statusCode=='IMAGEFILEWRITE'){
                this.form=new FormData();
                alert("이미지 파일 크기가 너무 큽니다.")
            }
            if(result.body.result=='SUCCESS'){
                this.$router.push('')
            }
        }, (error) => {
        console.error(error.data)
      })
    }
  }
};
</script>
<style scoped>
.md-layout {
  height: 100%;
}
.md-card-header > img {
  border-radius: 0%;
}
.active{
  width:400px;
  height: 200px;
}
.md-content {
  margin-top: 5%;
  height: 300px;
  min-width: 50%;
  overflow: scroll;
}
</style>



