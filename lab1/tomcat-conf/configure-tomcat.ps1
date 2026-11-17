# Скрипт для Windows PowerShell 5.1 или PowerShell 7.
# Запуск: .\configure-tomcat.ps1 -TomcatHome "C:\apache-tomcat-9.0.XXX"
param(
    [Parameter(Mandatory = $true)]
    [string] $TomcatHome
)
$ErrorActionPreference = 'Stop'
$path = Join-Path $TomcatHome 'conf\server.xml'
if (-not (Test-Path -LiteralPath $path)) { throw "Файл не найден: $path" }
$backup = "$path.bak"
Copy-Item -LiteralPath $path -Destination $backup -Force
[xml] $doc = Get-Content -LiteralPath $path -Encoding UTF8 -Raw
$service = $doc.Server.Service
if ($null -eq $service) { throw 'Нет секции Service в server.xml' }
$base = @($service.Connector) | Where-Object { $_.port -eq '8080' } | Select-Object -First 1
if ($null -eq $base) { throw 'Не найден Connector HTTP с портом 8080' }
$base.SetAttribute('useBodyEncodingForURI', 'true')
$extra = @($service.Connector) | Where-Object { $_.port -eq '8081' } | Select-Object -First 1
if ($null -eq $extra) {
    $extra = $base.CloneNode($true)
    $extra.SetAttribute('port', '8081')
    [void] $service.InsertAfter($extra, $base)
} else {
    $extra.SetAttribute('useBodyEncodingForURI', 'true')
}
$doc.Save($path)
Write-Host 'Изменения внесены в:' $path
Write-Host 'Резервная копия:' $backup
Write-Host 'Проверьте работу Tomcat на http://localhost:8080/ и http://localhost:8081/'
