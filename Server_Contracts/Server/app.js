'use strict';

const config = require('config');
const SwaggerExpress = require('swagger-express-mw');
const app = require('express')();

module.exports = app;

const swaggerConfig = {
    appRoot: __dirname, // required config
};

SwaggerExpress.create(swaggerConfig, async (err, swaggerExpress) => {
    if (err) {
        throw err;
    }

    // install middleware
    swaggerExpress.register(app);

    const port = config.get('service.port');
    app.listen(port);
});