
    FROM node:14.17.0-alpine as build-step
    RUN mkdir -p /app
    WORKDIR /app
    COPY package.json /app
    RUN npm install
    COPY . /app
    RUN npm run build
	
	FROM nginx:1.20.1
	#COPY default.conf /etc/nginx/conf.d/default.conf
    COPY --from=build-step /app/dist/mediscreen-angular-ui /usr/share/nginx/html
	EXPOSE 4200:4200
	CMD ["npm start"]
	
	#CMD ng serve --host 0.0.0.0
	# CMD ["ng", "serve", "--port=81"]
	
	# docker run -p 4200:4200  frontend-angular:latest
	
	# curl localhost:4200