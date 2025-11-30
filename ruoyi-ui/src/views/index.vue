<template>
  <div class="app-container home">
    <div class="gradient-showcase">
      <h2>主流渐变色推荐</h2>
      <div class="gradient-list">
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #52c41a 0%, #389e0d 100%)' }">
          <span>绿色渐变</span>
          <code>linear-gradient(90deg, #52c41a 0%, #389e0d 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #1890ff 0%, #096dd9 100%)' }">
          <span>蓝色渐变</span>
          <code>linear-gradient(90deg, #1890ff 0%, #096dd9 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #fa8c16 0%, #d46b08 100%)' }">
          <span>橙色渐变</span>
          <code>linear-gradient(90deg, #fa8c16 0%, #d46b08 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #f5222d 0%, #cf1322 100%)' }">
          <span>红色渐变</span>
          <code>linear-gradient(90deg, #f5222d 0%, #cf1322 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #722ed1 0%, #531dab 100%)' }">
          <span>紫色渐变</span>
          <code>linear-gradient(90deg, #722ed1 0%, #531dab 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #13c2c2 0%, #08979c 100%)' }">
          <span>青色渐变</span>
          <code>linear-gradient(90deg, #13c2c2 0%, #08979c 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #eb2f96 0%, #c41d7f 100%)' }">
          <span>粉色渐变</span>
          <code>linear-gradient(90deg, #eb2f96 0%, #c41d7f 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #faad14 0%, #d48806 100%)' }">
          <span>黄色渐变</span>
          <code>linear-gradient(90deg, #faad14 0%, #d48806 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #52c41a 0%, #1890ff 100%)' }">
          <span>绿蓝渐变</span>
          <code>linear-gradient(90deg, #52c41a 0%, #1890ff 100%)</code>
        </div>
        <div class="gradient-item" :style="{ background: 'linear-gradient(90deg, #fa8c16 0%, #eb2f96 100%)' }">
          <span>橙粉渐变</span>
          <code>linear-gradient(90deg, #fa8c16 0%, #eb2f96 100%)</code>
        </div>
      </div>
      <div class="usage-tip">
        <h3>使用提示</h3>
        <p>在绿色主题中，您可以直接替换以下文件中的渐变色：</p>
        <ul>
          <li><code>src/assets/styles/themes/theme-green.scss</code> - 侧边栏和菜单渐变</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.9.0"
    }
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank")
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  .gradient-showcase {
    padding: 20px;
    background: #f5f7fa;
    border-radius: 8px;
    margin: 20px;

    h2 {
      color: #303133;
      margin-bottom: 20px;
      font-weight: 600;
    }

    .gradient-list {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
      gap: 16px;
      margin-bottom: 24px;
    }

    .gradient-item {
      padding: 20px;
      border-radius: 8px;
      color: white;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      transition: transform 0.3s ease, box-shadow 0.3s ease;

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 16px 0 rgba(0, 0, 0, 0.15);
      }

      span {
        display: block;
        font-size: 16px;
        font-weight: 600;
        margin-bottom: 8px;
      }

      code {
        display: block;
        font-size: 12px;
        background: rgba(255, 255, 255, 0.2);
        padding: 8px;
        border-radius: 4px;
        word-break: break-all;
      }
    }

    .usage-tip {
      background: white;
      padding: 20px;
      border-radius: 8px;
      border: 1px solid #ebeef5;

      h3 {
        color: #303133;
        margin-bottom: 12px;
        font-weight: 600;
      }

      p {
        margin-bottom: 12px;
      }

      ul {
        list-style-type: disc;
        padding-left: 20px;

        li {
          margin-bottom: 8px;

          code {
            background: #f5f7fa;
            padding: 2px 6px;
            border-radius: 3px;
            font-size: 12px;
            color: #e74c3c;
          }
        }
      }
    }
  }
}
</style>

