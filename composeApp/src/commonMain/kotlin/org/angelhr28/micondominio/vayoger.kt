package org.angelhr28.micondominio

//class FirstScreen : cafe.adriel.voyager.core.screen.Screen {
//
//
//    @Composable
//    override fun Content() {
//        val navigator: Navigator = LocalNavigator.currentOrThrow
//
//        Column(
//            modifier = Modifier
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = {
//                navigator.push(SecondScreen())
//            }) {
//                Text("Click me!")
//            }
//        }
//    }
//}
//
//
//class SecondScreen : cafe.adriel.voyager.core.screen.Screen {
//    @Composable
//    override fun Content() {
//        val navigator: Navigator = LocalNavigator.currentOrThrow
//
//        Column(
//            modifier = Modifier
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = {
//                navigator.pop()
//            }) {
//                Text("VOLVER ")
//            }
//
//        }
//    }
//}