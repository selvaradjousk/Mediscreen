
# Stage 1: Compile and Build angular codebase

# Use official node image as the base image
FROM node:latest as builder

# Set the working directory
RUN mkdir -p /app
WORKDIR /app

RUN npm install -g @angular/cli

# Add the source code to app
# COPY . .
COPY ./ /app/

# Install all the dependencies
RUN npm install

# Generate the build of the application
RUN npm run build --prod

# Stage 2: Serve app with nginx server

# Use official nginx image as the base image
#FROM nginx:latest

# Copy the build output to replace the default nginx contents.
#COPY --from=build /app/dist/demo /usr/share/nginx/html

CMD ["npm", "start"]