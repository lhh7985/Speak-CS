module.exports = {
  devServer: {
    https: false,
    port: 8088,
    open: true,
    proxy: {
      "/": {
        target: "http://localhost:8080",
        changeOrigin: true,
        logLevel: "debug",
      },
    },
    historyApiFallback: true,
    hot: true,
  },
  pluginOptions: {
    quasar: {
      importStrategy: "kebab",
      rtlSupport: false,
    },
  },
  transpileDependencies: ["quasar"],
};
