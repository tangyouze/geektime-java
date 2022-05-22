
@task
def start_redis(ctx):
    ctx.run('docker run -it -p 13001:6379  -d --name redis  redis:5')