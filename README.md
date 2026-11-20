# BLOOD — Android starter project

Primeira versão de um companion de desempenho para Android.

## O que esta versão faz
- Exibe uma interface escura em preto e vermelho.
- Abre o aplicativo oficial do Roblox, se estiver instalado.
- Se Roblox não estiver instalado, abre sua página oficial na Play Store.
- Reúne orientações seguras para reduzir travamentos.

## O que esta versão NÃO faz
- Não é um cliente Roblox modificado.
- Não coleta login, senha, cookies ou tokens.
- Não altera gráficos internos do Roblox.
- Não encerra processos do Android nem promete aumento de FPS.

## Como gerar o APK
1. Abra esta pasta no Android Studio em um computador.
2. Aguarde a sincronização do Gradle.
3. Se necessário, instale Android SDK 35.
4. Use **Build > Build APK(s)**.
5. O APK de debug ficará em `app/build/outputs/apk/debug/`.

Este projeto usa Kotlin, Jetpack Compose e Android Gradle Plugin 8.7.3.
