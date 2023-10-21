package com.example.plugins

import com.example.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/"){
            get {
                val user=User("Ted Omino",24,"get_root")
                call.respond(HttpStatusCode.OK,user)
            }
            post {
                val user=User("Brayden Omino",10,"post_root")
                call.respond(HttpStatusCode.OK,user)
            }
        }
        route("/home"){
            get {
                val user=User("Israel Omino",20,"get_home_root")
                call.respond(HttpStatusCode.OK,user)
            }
            post {
                val user=User("Young Omino",29,"post_home_root")
                call.respond(HttpStatusCode.OK,user)
            }
        }
    }
}
